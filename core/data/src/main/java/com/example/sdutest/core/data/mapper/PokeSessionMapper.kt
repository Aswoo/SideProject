package com.example.sdutest.core.data.mapper

import com.example.sdutest.core.data.model.PokeSessionResponse
import com.example.sdutest.core.data.model.SessionResponse
import com.example.sdutest.core.model.PokeSession
import com.example.sdutest.core.model.Room
import com.example.sdutest.core.model.Session
import com.example.sdutest.core.model.Tag

internal fun PokeSessionResponse.toData(): PokeSession = PokeSession(
    id = this.id,
    name = this.name,
    types = this.types,
    height = this.height,
    weight = this.weight,
    sex = this.sex,
    category = this.category,
    content = this.content,
    image = this.image,
    enName = this.name,
    isBookmarked = false
)