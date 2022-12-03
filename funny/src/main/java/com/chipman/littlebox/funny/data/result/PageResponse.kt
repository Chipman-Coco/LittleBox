package com.chipman.littlebox.funny.data.result

/**
 * 列表数据
 */
class PageResponse<T>(
    val page: Int,          // 当前页数
    val totalCount: Int,    // 总数量
    val totalPage: Int,     // 总页数
    val limit: Int,         // 每页数量
    val list: List<T>       // 每页具体数据
)