package com.example.sdutest.feature.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.sdutest.core.designsystem.component.KnightsTopAppBar
import com.example.sdutest.core.designsystem.component.TextChip
import com.example.sdutest.core.designsystem.component.TopAppBarNavigationType
import com.example.sdutest.core.designsystem.component.NetworkImage
import com.example.sdutest.core.designsystem.theme.DarkGray
import com.example.sdutest.core.designsystem.theme.KnightsTheme
import com.example.sdutest.core.designsystem.theme.LightGray
import com.example.sdutest.core.model.Speaker
import com.example.sdutest.core.model.Tag
import com.example.sdutest.core.model.pokemon.GameIndices
import com.example.sdutest.core.model.pokemon.PokemonResponse
import com.example.sdutest.core.model.pokemon.Types
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.delay
import kotlinx.datetime.LocalDateTime

@Composable
internal fun SessionDetailScreen(
    sessionId: String,
    onBackClick: () -> Unit,
    viewModel: SessionDetailViewModel = hiltViewModel(),
) {
    val scrollState = rememberScrollState()
    val sessionUiState by viewModel.sessionUiState.collectAsStateWithLifecycle()
    val effect by viewModel.sessionUiEffect.collectAsStateWithLifecycle()

    val context = LocalContext.current

    LaunchedEffect(effect) {
        if (effect is SessionDetailEffect.ShowToastForBookmarkState) {
//            sendWidgetUpdateCommand(context)
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(androidx.compose.ui.graphics.Color.White)
            .systemBarsPadding()
            .verticalScroll(scrollState),
    ) {
        SessionDetailTopAppBar(
            bookmarked = (sessionUiState as? SessionDetailUiState.Success)?.bookmarked ?: false,
            onClickBookmark = { viewModel.toggleBookmark() },
            onBackClick = onBackClick
        )
        Box {
            SessionDetailContent(uiState = sessionUiState)
            if (effect is SessionDetailEffect.ShowToastForBookmarkState) {
                SessionDetailBookmarkStatePopup(
                    bookmarked = (effect as SessionDetailEffect.ShowToastForBookmarkState).bookmarked
                )
            }
        }
    }

    LaunchedEffect(sessionId) {
        viewModel.fetchPokemon(sessionId)
    }

    LaunchedEffect(effect) {
        if (effect is SessionDetailEffect.ShowToastForBookmarkState) {
            delay(1000L)
            viewModel.hidePopup()
        }
    }
}

@Composable
private fun SessionDetailTopAppBar(
    bookmarked: Boolean,
    onClickBookmark: (Boolean) -> Unit,
    onBackClick: () -> Unit,
) {
    KnightsTopAppBar(
        titleRes = R.string.session_detail_title,
        navigationIconContentDescription = null,
        navigationType = TopAppBarNavigationType.Back,
        actionButtons = {
            BookmarkToggleButton(
                bookmarked = bookmarked,
                onClickBookmark = onClickBookmark
            )
        },
        onNavigationClick = onBackClick,
    )
}

@Composable
private fun SessionDetailContent(uiState: SessionDetailUiState) {
    when (uiState) {
        is SessionDetailUiState.Loading -> SessionDetailLoading()
        is SessionDetailUiState.Success -> SessionDetailContent(uiState.pokemonRes)
    }
}

@Composable
private fun SessionDetailLoading() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
private fun SessionDetailContent(session: PokemonResponse) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        val url = session.sprites!!.backDefault
        SessionDetailTitle(title = session.name.toString(), modifier = Modifier.padding(top = 8.dp))
        Spacer(modifier = Modifier.height(8.dp))
        Row(modifier = Modifier.fillMaxWidth(),Arrangement.Center) {
            NetworkImage(
                imageUrl = session.sprites!!.frontDefault,
                modifier = Modifier
                    .size(150.dp),
                contentScale = ContentScale.FillBounds,
                placeholder = null,
            )
            Spacer(modifier = Modifier.width(16.dp))
            NetworkImage(
                imageUrl = session.sprites!!.backDefault,
                modifier = Modifier
                    .size(150.dp),
                contentScale = ContentScale.FillBounds,
                placeholder = null,
            )
        }
        SessionChips(session = session.gameIndices)
        Spacer(modifier = Modifier.height(20.dp))
        Divider(thickness = 1.dp, color = MaterialTheme.colorScheme.outline)
        Spacer(modifier = Modifier.height(20.dp))
        SessionDetailSpeaker(
            pokeId = session.id,
            pokeTypes = session.types.toList(),
            pokeHeight = session.height,
            pokeWeight = session.weight,
        )
    }
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun SessionChips(session: List<GameIndices>) {
    Column {
        Text(text = "출현 버전", style = KnightsTheme.typography.titleLargeM)
        val tagList = session.map { it.version!!.name }.toPersistentList()
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            TagChips(tags = tagList)
        }
    }

}

@Composable
private fun TagChips(tags: PersistentList<String?>) {

    tags.forEach { tag ->
        TagChip(tag = tag.toString())
    }
}

@Composable
private fun TagChip(tag: String) {
    Box(modifier = Modifier.padding(0.dp, 4.dp)) {
        TextChip(
            text = tag,
            containerColor = DarkGray,
            labelColor = LightGray,
        )
    }

}

@Composable
private fun SessionDetailTitle(
    title: String,
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier.padding(end = 64.dp),
        text = title,
        style = KnightsTheme.typography.titleLargeM,
        color = MaterialTheme.colorScheme.onSurface,
    )
}

//session.id,session.types,session.height,session.weight
@Composable
private fun SessionDetailSpeaker(
    pokeId: Int?,
    pokeTypes: List<Types>,
    pokeHeight: Int?,
    pokeWeight: Int?,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {

        Text(
            text = "ID : $pokeId",
            style = KnightsTheme.typography.titleMediumB,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
        )

        Spacer(Modifier.height(16.dp))

        Text(
            text = "Type : ${pokeTypes.joinToString(",") { it.type?.name!! }} ",
            style = KnightsTheme.typography.titleMediumB,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
        )
        Text(
            text = "Height : $pokeHeight m",
            style = KnightsTheme.typography.titleMediumB,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
        )
        Text(
            text = "Weight : $pokeWeight kg",
            style = KnightsTheme.typography.titleMediumB,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
        )
        Spacer(Modifier.height(16.dp))
    }
}

@Composable
private fun SessionOverview(content: String) {
    Text(
        text = content,
        style = KnightsTheme.typography.titleSmallR140,
        color = MaterialTheme.colorScheme.onSecondaryContainer
    )
}

@Composable
private fun BookmarkToggleButton(
    bookmarked: Boolean,
    onClickBookmark: (Boolean) -> Unit
) {
    IconToggleButton(
        checked = bookmarked,
        onCheckedChange = onClickBookmark
    ) {
        Icon(
            painter =
            if (bookmarked) {
                painterResource(id = R.drawable.ic_session_bookmark_filled)
            } else {
                painterResource(id = R.drawable.ic_session_bookmark)
            },
            contentDescription = null
        )
    }
}

@Preview
@Composable
private fun SessionDetailTopAppBarPreview() {
    KnightsTheme {
        var state by remember { mutableStateOf(false) }
        SessionDetailTopAppBar(
            bookmarked = state,
            onClickBookmark = {
                state = it
            }
        ) { }
    }
}


@Preview
@Composable
private fun SessionDetailTitlePreview() {
    KnightsTheme {
        SessionDetailTitle(title = "sample detail title")
    }
}
