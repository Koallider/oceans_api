package com.example.oceans_api.controller

import com.example.oceans_api.domain.OceanResponse
import com.example.oceans_api.service.OceanService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
class OceanController {

    @Autowired
    lateinit var oceanService: OceanService

    //todo not found
    @GetMapping("/search")
    fun getOceanForBounds(
        @RequestParam("minx") minx: Double,
        @RequestParam("miny") miny: Double,
        @RequestParam("maxx") maxx: Double,
        @RequestParam("maxy") maxy: Double,
        @RequestParam("exclude") exclude: Collection<Int>?
    ): OceanResponse {
        try {
            return oceanService.getPolygonForBounds(
                minx, miny, maxx, maxy, exclude
            )
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource")
        }
    }

}