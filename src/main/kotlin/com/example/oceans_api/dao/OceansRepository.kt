package com.example.oceans_api.dao

import com.example.oceans_api.domain.Ocean
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface OceansRepository : CrudRepository<Ocean, Int> {
    fun findByGid(gid: Int): Ocean

    @Query(value = "Select gid, ST_AsGeoJSON(geom)::jsonb as geom from public.oceans where ST_Intersects(ST_MakeBox2D(ST_MakePoint(?1, ?2), ST_MakePoint(?3, ?4)), geom)", nativeQuery = true)
    fun findOceanByBounds(minx: Double, miny: Double, maxx: Double, maxy: Double): Collection<Ocean>

    @Query(value = "Select gid, ST_AsGeoJSON(geom)::jsonb as geom from public.oceans where ST_Intersects(ST_MakeBox2D(ST_MakePoint(?1, ?2), ST_MakePoint(?3, ?4)), geom) AND gid not in ?5", nativeQuery = true)
    fun findOceanByBoundsAndExcludeExisting(minx: Double, miny: Double, maxx: Double, maxy: Double, exclude: Collection<Int>): Collection<Ocean>
}