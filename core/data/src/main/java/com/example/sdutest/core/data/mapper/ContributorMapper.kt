package com.example.sdutest.core.data.mapper

import com.example.sdutest.core.data.model.ContributorResponse
import com.example.sdutest.core.model.Contributor

internal fun ContributorResponse.toData(): Contributor =
    Contributor(
        name = this.name,
        imageUrl = this.imageUrl,
        githubUrl = this.githubUrl,
    )
