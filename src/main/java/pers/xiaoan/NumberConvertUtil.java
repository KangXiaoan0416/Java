package pers.xiaoan;

import java.util.*;

/**
 * 号码转存工具类
 * 思考（待优化）:当列表数据的话,是否可以进行多线程操作，加快执行速度
 * 测试：1000万数据，600万重复，耗时8000ms; 测试数据生成机制不完善,可以改进
 * jdk版本:1.8
 * @author: kangxiaoan
 * @date: 2019/6/20 11:23
 */
public class NumberConvertUtil {
    /** 冗余字段KEY */
    private final String KEY_REDUNDANCY = "redundancy";
    /** 拆分结果KEY */
    private final String KEY_SPLIT = "split";
    /** 有效数据KEY */
    private final String KEY_EFFECTIVE = "effective";
    private final int DEFAULT_EFFECTIVE_NUM = 3;
    /** 是否打印有效数据和冗余数据列表信息 */
    private boolean printList = false;

    /** 是否打印号段map */
    private boolean printNumMap = false;

    private volatile static NumberConvertUtil instance;
    private NumberConvertUtil() {}
    public static NumberConvertUtil getInstance() {
        if(instance == null) {
            synchronized (NumberConvertUtil.class) {
                if(instance == null) {
                    instance = new NumberConvertUtil();
                }
            }
        }
        return instance;
    }

    /**
     * 号段转存进map
     * @param numberList 数字列表集合
     * @param effectiveNum 有效数字 默认前三位
     * @author kangxiaoan
     * @crateDate 2019/6/20 11:40
     * @return java.util.Map<java.lang.String, java.lang.Object>
     */
    private Map<String, Object> convertFromNumberList(List<String> numberList, int effectiveNum) {
        effectiveNum = effectiveNum == 0?DEFAULT_EFFECTIVE_NUM:effectiveNum;
        /** 重复号码总数 */
        int repeatTotalNum = 0;
        long begin = System.currentTimeMillis();

        Map<String, Object> resultMap = new HashMap<>(16);
        /** 号码分解结果 map */
        Map<Integer, Object> splitMap = new HashMap<>(16);
        /** 消重 map */
        Map<String, Integer> repeatMap = new HashMap<>(16);
        /** 冗余数据 list */
        List<String> redundancyList = new ArrayList();
        /** 有效数据 list */
        List<String> effectiveList = new ArrayList<>();

        if(numberList != null && numberList.size() > 0) {
            // 号段列表重新排序 按长度从小到大
            Collections.sort(numberList, Comparator.comparingInt(String::length));

            // 号段第一个号码都是1,初始化map
            for (String numberStr : numberList) {

                numberStr = numberStr.trim();
                int length = numberStr.length();
                if(!repeatMap.containsKey(numberStr)) {
                    repeatMap.put(numberStr, 1);
                }else {
                    repeatTotalNum ++;
                    repeatMap.put(numberStr, repeatMap.get(numberStr) + 1);
                    continue;
                }

                Map<Integer, Object> nodeMap = null;
                Object value;
                int lastNum = 0;
                boolean effective = true;
                for(int i = 0; i < length; i++) {
                    int num = convertCharToInt(numberStr.charAt(i));
                    if(i == 0) {
                        nodeMap = splitMap;
                        if(!nodeMap.containsKey(num)) {
                            nodeMap.put(num, null);
                        }
                        lastNum = num;
                        continue;
                    }

                    if(nodeMap.containsKey(lastNum)) {
                        value = nodeMap.get(lastNum);
                        Map<Integer, Object> itemMap;
                        if(value == null) {
                            itemMap = new HashMap<>();
                            itemMap.put(num, null);
                            nodeMap.put(lastNum, itemMap);
                        }else {
                            itemMap = (Map<Integer, Object>) value;
                            if(!itemMap.containsKey(num)) {
                                itemMap.put(num, null);
                            }else {
                                // 冗余数据,判断是否有下一个节点(即最小长度真实存在号段)，如果有，说明当前不是有效数据
                                if(itemMap.get(num) == null && i < effectiveNum) {
                                    effective = false;
                                    break;
                                }
                            }
                        }
                        lastNum = num;
                        nodeMap = itemMap;
                    }else {
                        nodeMap.put(num, null);
                    }
                }
                if(effective) {
                    effectiveList.add(numberStr);
                }else {
                    redundancyList.add(numberStr);
                }
            }
        }
        if(printNumMap) {
            showSplitMap(splitMap, null, 0);
        }
        if(printList) {
            printList(KEY_EFFECTIVE, effectiveList);
            printList(KEY_REDUNDANCY, redundancyList);
        }
        resultMap.put(KEY_SPLIT, splitMap);
        resultMap.put(KEY_EFFECTIVE, effectiveList);
        resultMap.put(KEY_REDUNDANCY, redundancyList);

        long end = System.currentTimeMillis();
        System.out.println("号段信息处理结束，共耗时：" + (end - begin) + "ms.");
        System.out.println("重复数据共:" + repeatTotalNum + "条");
        return resultMap;
    }
    
    /**
     * 字符转换成数字
     * @param num
     * @author kangxiaoan
     * @crateDate 2019/6/20 16:53
     * @return int
     */
    private int convertCharToInt(char num) {
        int result = 0;
        if(num != '\0') {
            result = (int) num - (int) ('0');
        }
        return result;
    }



    /**
     * 打印列表信息
     * @param columnName
     * @param list
     * @Author: xiaoan
     * @Date: 2019/6/22
     * @return: void
     */
    private void printList(String columnName, List<String> list) {
        System.out.print(columnName + ":");
        if(list != null && list.size() > 0) {
            for (String item : list) {
                System.out.print(item + "\t");
            }
        }
        System.out.println();
    }

    /**
     * 显示拆分结果map信息, 水平显示
     * @param map 结果Map
     * @param nextList 下一次执行列表
     * @param i 第几次执行
     * @Author: xiaoan
     * @Date: 2019/6/22
     * @return: void
     */
    private void showSplitMap(Map<Integer, Object> map, List<Map<Integer, Object>> nextList, int i) {
        if((map != null && map.size() > 0 || (nextList != null && nextList.size() > 0))) {
            if(i == 0) {
                System.out.println("null 代表对应号码已结束，字节点无数据，起占位作用，以便关系对应上");
            }
            if((nextList == null || nextList.size() == 0) && (map != null && map.size() > 0)) {
                Map<String, Object> result = getKeyAndValue(map);
                System.out.println();
                nextList = (List<Map<Integer, Object>>)result.get("value");
                if(nextList.size() > 0) {
                    showSplitMap(null, nextList, i);
                }
            }else if((map == null || map.size() == 0) && (nextList != null && nextList.size() > 0)) {
                List<Map<Integer, Object>> nextAllList = new ArrayList<>();
                for (Map<Integer,Object> item : nextList) {
                    Map<String, Object> result = getKeyAndValue(item);
                    List<Map<Integer, Object>> resultList = (List<Map<Integer, Object>>)result.get("value");
                    nextAllList.addAll(resultList);
                }
                System.out.println();
                showSplitMap(null, nextAllList, i);
            }
        }
    }

    /**
     * 打印map中的Key 和valueList
     * @param map
     * @Author: xiaoan
     * @Date: 2019/6/22
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    private Map<String, Object> getKeyAndValue(Map<Integer, Object> map) {
        Map<String, Object> result = new HashMap<>();
        List<Map<Integer, Object>> nextList = new ArrayList<>();
        if(map != null ) {
            StringBuilder sb = new StringBuilder("");
            int i = 0;
            for(Map.Entry<Integer, Object> entry : map.entrySet()) {
                i++;
                Object obj = entry.getValue();
                int key = entry.getKey();
                sb.append(key);
                if(i < map.size()) {
                    sb.append(" ");
                }
                if(obj != null) {
                    Map<Integer, Object> itemMap = (Map<Integer, Object>) obj;
                    nextList.add(itemMap);
                }else {
                    nextList.add(null);
                }
            }
            if(i > 1) {
                sb.insert(0, "(").append(")");
            }
            System.out.print(sb.toString()  + " ");
        }else {
            System.out.print("null" + " ");
        }
        result.put("value", nextList);
        return result;
    }

    /**
     * 制作号段信息列表
     * @param num
     * @Author: xiaoan
     * @Date: 2019/6/22
     * @return: java.util.List<java.lang.String>
     */
    private List<String> initData(int num) {
        List<String> result = null;
        if(num > 0) {
            result = new ArrayList<>();
            Random random =  new Random();
            for(int i = 0; i < num; i++) {
                int length = random.nextInt(8) + 3;
                StringBuilder sb = new StringBuilder("1");
                for(int j = 1; j < length; j++) {
                    int value;
                    if(j == 1) {
                        value = random.nextInt(6) + 3;
                    }else {
                        value = random.nextInt(9);
                    }
                    sb.append(value);
                }
                result.add(sb.toString());
            }
        }
        return result;
    }

    public static void main(String[] args) {
        NumberConvertUtil instance = NumberConvertUtil.getInstance();


        List<String> numberList = new ArrayList<>();
        /* numberList.add("139123");
        numberList.add("1358970");
        numberList.add("1391");
        numberList.add("1396");
        numberList.add("13977");*/
        numberList = instance.initData(10000000);

        NumberConvertUtil.getInstance().convertFromNumberList(numberList, 0);

    }
}

