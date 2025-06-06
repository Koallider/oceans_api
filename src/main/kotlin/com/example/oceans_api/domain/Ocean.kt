package com.example.oceans_api.domain

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "OCEANS", schema="public")
data class Ocean(@Id val gid: Int, val geom: String)
