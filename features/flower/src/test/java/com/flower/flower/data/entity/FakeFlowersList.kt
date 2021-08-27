package com.flower.flower.data.entity

import com.flower.flower.data.mapper.FlowersListEntityToModelMapper
import com.flower.flower.domain.model.FlowersListModel
import com.flower.remote.utils.DataSource
import com.flower.remote.utils.FailureData
import com.flower.remote.utils.NetworkCodes
import com.flower.remote.utils.Resource
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeFlowersList {

    private val mapper = FlowersListEntityToModelMapper()
    fun getFlowersListResponse(): FlowersListResponse {
        return createFlowersFromJson() ?: throw Exception()
    }

    fun getMappedData(): FlowersListModel {
        return mapper.map(getFlowersListResponse())
    }

    fun getFailureData(): FailureData {
        val errorCode = NetworkCodes.CONNECTION_ERROR
        val errorScript = "Failed to connect to server please check you network!"
        return FailureData(errorCode, errorScript)
    }

    fun getSuccessEmit(): Flow<Resource<FlowersListModel>> = flow {
        emit(Resource.Loading)
        emit(Resource.Success(mapper.map(getFlowersListResponse()), DataSource.REMOTE))
    }

    fun getFailureEmit(): Flow<Resource<FlowersListModel>> = flow {
        val errorCode = NetworkCodes.CONNECTION_ERROR
        val errorScript = "Failed to connect to server please check you network!"
        emit(Resource.Loading)
        emit(Resource.Failure(failureData = FailureData(errorCode, errorScript)))
    }

    private fun createFlowersFromJson(): FlowersListResponse? {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val jsonAdapter = moshi.adapter(FlowersListResponse::class.java)
        return jsonAdapter.fromJson(flowersListResponse)
    }

    private val flowersListResponse =
        "{\"flowers\":[{\"id\":7,\"name\":\"Alpski volcin\",\"latin_name\":\"Daphne alpina\",\"sightings\":13,\"profile_picture\":\"//flowrspot.s3.amazonaws.com/flowers/profile_pictures/000/000/007/medium/L_00010.jpg?1527514226\",\"favorite\":false},{\"id\":28,\"name\":\"bb\",\"latin_name\":\"bb\",\"sightings\":0,\"profile_picture\":\"https://flowrspot-api.herokuapp.com/images/medium/missing.jpg\",\"favorite\":false},{\"id\":14,\"name\":\"Bee orchid\",\"latin_name\":\"Ophrys apifera\",\"sightings\":1,\"profile_picture\":\"//flowrspot.s3.amazonaws.com/flowers/profile_pictures/000/000/014/medium/L_00010.jpg?1527514642\",\"favorite\":false},{\"id\":8,\"name\":\"Checkered Lily\",\"latin_name\":\"Fritillaria meleagris\",\"sightings\":3,\"profile_picture\":\"//flowrspot.s3.amazonaws.com/flowers/profile_pictures/000/000/008/medium/L_9_0972.JPG?1527514310\",\"favorite\":false},{\"id\":25,\"name\":\"Cherry Blossom\",\"latin_name\":\"Cherry Spinosa\",\"sightings\":1,\"profile_picture\":\"https://flowrspot-api.herokuapp.com/images/medium/missing.jpg\",\"favorite\":false},{\"id\":5,\"name\":\"Daisy\",\"latin_name\":\"Bellis perennis\",\"sightings\":3,\"profile_picture\":\"//flowrspot.s3.amazonaws.com/flowers/profile_pictures/000/000/005/medium/L_3-0365_IMG.JPG?1527514193\",\"favorite\":false},{\"id\":12,\"name\":\"Drobnolistni lan\",\"latin_name\":\"Linum tenuifolium\",\"sightings\":1,\"profile_picture\":\"//flowrspot.s3.amazonaws.com/flowers/profile_pictures/000/000/012/medium/L_2-6230_IMG.JPG?1527514470\",\"favorite\":false},{\"id\":1,\"name\":\"Goosefoot Violet\",\"latin_name\":\"Viola purpurea\",\"sightings\":9,\"profile_picture\":\"//flowrspot.s3.amazonaws.com/flowers/profile_pictures/000/000/001/medium/viola.jpg?1505132614\",\"favorite\":false},{\"id\":15,\"name\":\"Julijski mak\",\"latin_name\":\"Papaver alpinum ssp. ernesti-mayeri\",\"sightings\":0,\"profile_picture\":\"//flowrspot.s3.amazonaws.com/flowers/profile_pictures/000/000/015/medium/T_120010.JPG?1527514700\",\"favorite\":false},{\"id\":24,\"name\":\"Lilith\",\"latin_name\":\"Lilith Spinosa\",\"sightings\":0,\"profile_picture\":\"https://flowrspot-api.herokuapp.com/images/medium/missing.jpg\",\"favorite\":false}],\"meta\":{\"pagination\":{\"current_page\":1,\"prev_page\":null,\"next_page\":2,\"total_pages\":4}}}"
}
