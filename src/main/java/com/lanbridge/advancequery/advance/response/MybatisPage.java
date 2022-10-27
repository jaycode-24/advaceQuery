package com.lanbridge.advancequery.advance.response;

import java.util.List;

/**
 * @Description mybatis分页
 * @Author wangcheng
 * @Date 2022/10/25 22:38
 */
public class MybatisPage {

    private final static ThreadLocal<Integer> TOTAL_COUNT = new ThreadLocal<>();
    private final static ThreadLocal<Integer> TOTAL_PAGE = new ThreadLocal<>();

    /**
     * 检查返回结果是否是分页结果
     * @return boolean
     */
    public static boolean isPageRequest(){
        return TOTAL_PAGE.get() != null && TOTAL_COUNT.get() != null;
    }

    public static void remove(){
        TOTAL_COUNT.remove();
        TOTAL_PAGE.remove();
    }

    public static <T> PageList<T> pageList(List<T> result) {
        PageList<T> pageList = new PageList<>(result, TOTAL_COUNT.get(), TOTAL_PAGE.get());
        remove();
        return pageList;
    }

    public static void stashResult(int totalCount,int pageSize) {
        Integer totalPage = (totalCount - 1)/pageSize + 1;
        TOTAL_COUNT.set(totalCount);
        TOTAL_PAGE.set(totalPage);
    }
}
