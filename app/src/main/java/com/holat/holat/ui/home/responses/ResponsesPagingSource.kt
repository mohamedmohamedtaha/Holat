package com.holat.holat.ui.home.responses

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.holat.holat.data.network.MainRepository
import com.holat.login.models.responses.ResponsesResponse

/**
Created by Mohamed Mohamed Taha on 3/4/2024
 */
private const val STARTING_KEY = 1

//private val firstConversationCreatedTime = LocalDateTime.now()
class ResponsesPagingSource(
    private val mainRepository: MainRepository,
    private val ticketId: Long, private val page: Int, private val perPage: Int
) : PagingSource<Int, ResponsesResponse>() {
    private fun ensureValidKEy(key: Int) = maxOf(STARTING_KEY, key)

    // The refresh key is used for the initial load of the next PagingSource, after invalidation
//    override fun getRefreshKey(state: PagingState<Int, DataResponses>): Int? {
//       val anchorPosition = state.anchorPosition ?: return null
//        val conversation = state.closestItemToPosition(anchorPosition) ?: return null
//        return ensureValidKEy(key = conversation.id - (state.config.pageSize / 2))
//    }
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DataResponses> {
//        // Start paging with the STARTING_KEY if this is the first load
//        val start = params.key ?: STARTING_KEY
//        //Load as many items as hinted by params.loadSize
//        val range = start.until(start + params.loadSize)
//        return try {
//            val response = loginRepository.getResponsesPaging(ticketId= ticketId, page = start, perPage = params.loadSize)
//            val repos = response
//        }
//        return LoadResult.Page(data = range.map {number->
//            // Generate consecutive increasing numbers as the ticket id
//            //DataResponses(ticket_id = number)
//        },
//            //Make sure we don't try to load items behind the STARTING_KEY
//    prevKey = when(start){
//        STARTING_KEY -> null
//        else -> ensureValidKEy(key = range.first - params.loadSize)
//    }, nextKey = range.last + 1)
//
//    }

    override fun getRefreshKey(state: PagingState<Int, ResponsesResponse>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResponsesResponse> {
        TODO("Not yet implemented")
    }
}