package com.example.oceans_api.service

import com.example.oceans_api.dao.OceansRepository
import com.example.oceans_api.domain.OceanResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class OceanService {

    @Autowired
    lateinit var oceanRepository: OceansRepository

    fun getPolygonForBounds(
        minx: Double,
        miny: Double,
        maxx: Double,
        maxy: Double,
        exclude: Collection<Int>?
    ): OceanResponse {
        val oceans =  if (exclude.isNullOrEmpty()) {
            oceanRepository.findOceanByBounds(minx, miny, maxx, maxy)
        } else {
            oceanRepository.findOceanByBoundsAndExcludeExisting(minx, miny, maxx, maxy, exclude)
        }
        return OceanResponse(oceans)
    }

}