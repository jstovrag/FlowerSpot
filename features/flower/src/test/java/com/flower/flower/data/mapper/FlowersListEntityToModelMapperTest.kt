package com.flower.flower.data.mapper

import com.flower.flower.data.entity.FakeFlowersList
import com.flower.flower.data.entity.FlowersListResponse
import com.flower.flower.domain.model.FlowersListModel
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class FlowersListEntityToModelMapperTest {

    private lateinit var testMapper: FlowersListEntityToModelMapper

    @Before
    fun setup() {
        testMapper = FlowersListEntityToModelMapper()
    }

    @Test
    fun `Given test entity, when map, then return expected model`() {
        val tempTests = FakeFlowersList().getFlowersListResponse()
        val expectedModel: FlowersListModel

        tempTests.let { response ->
            expectedModel = mapToFlowersListModel(response)
        }

        val result = testMapper.map(tempTests)

        Assert.assertEquals(result, expectedModel)
    }
}

private fun mapToFlowersListModel(response: FlowersListResponse): FlowersListModel {
    return FlowersListModel(
        flowers = response.flowers,
        meta = response.meta
    )
}
