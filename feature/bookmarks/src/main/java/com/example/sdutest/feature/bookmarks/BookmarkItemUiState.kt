package com.example.sdutest.feature.bookmarks

import com.example.sdutest.core.model.PokeSession
import com.example.sdutest.core.model.Session
import kotlinx.datetime.toJavaLocalDateTime
import java.time.LocalTime

data class BookmarkItemUiState(
    val index: Int,
    val session: PokeSession,
    val isEditMode: Boolean
) {
    val sequence: Int
        get() = index + 1

    val tagLabel: String
        get() = session.types.joinToString()

    val sexLabel: String
        get() = if(session.sex == null) {
            "알수없음"
        } else {
            session.sex!!.joinToString()
        }


}
