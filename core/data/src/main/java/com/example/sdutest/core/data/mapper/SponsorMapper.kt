package com.example.sdutest.core.data.mapper

import com.example.sdutest.core.data.model.SponsorResponse
import com.example.sdutest.core.model.Sponsor

internal fun SponsorResponse.toData(): Sponsor = Sponsor(
    name = name,
    imageUrl = imageUrl,
    homepage = homepage,
    grade = when (grade) {
        SponsorResponse.Grade.PLATINUM -> Sponsor.Grade.PLATINUM
        SponsorResponse.Grade.GOLD -> Sponsor.Grade.GOLD
    }
)
