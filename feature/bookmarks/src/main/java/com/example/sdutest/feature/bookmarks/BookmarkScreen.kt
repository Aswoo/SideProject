package com.example.sdutest.feature.bookmarks

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.sdutest.core.designsystem.theme.DuskGray
import com.example.sdutest.core.designsystem.theme.KnightsTheme
import com.example.sdutest.core.designsystem.theme.PaleGray
import com.example.sdutest.core.designsystem.theme.Purple01
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.collectLatest

@Composable
internal fun BookmarkRoute(
    onShowErrorSnackBar: suspend (String, String?) -> Boolean,
    viewModel: BookmarkViewModel = hiltViewModel()
) {
    val bookmarkUiState by viewModel.bookmarkUiState.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        viewModel.errorFlow.collectLatest { onShowErrorSnackBar(it,"error") }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .systemBarsPadding()
    ) {
        BookmarkContent(
            uiState = bookmarkUiState,
            onClickEditButton = { viewModel.clickEditButton() }
        )
    }
}

@Composable
private fun BookmarkContent(
    uiState: BookmarkUiState,
    onClickEditButton: () -> Unit
) {
    when (uiState) {
        BookmarkUiState.Loading -> BookmarkLoading()
        is BookmarkUiState.Success -> BookmarkScreen(
            isEditMode = uiState.isEditButtonSelected,
            bookmarkItems = uiState.bookmarks.toImmutableList(),
            onClickEditButton = onClickEditButton
        )
    }
}

@Composable
private fun BookmarkLoading() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
private fun BookmarkScreen(
    isEditMode: Boolean,
    bookmarkItems: ImmutableList<BookmarkItemUiState>,
    onClickEditButton: () -> Unit,
    listContentBottomPadding: Dp = 72.dp
) {
    Column(
        Modifier
            .padding(horizontal = 8.dp)
            .fillMaxSize()
            .background(color = PaleGray),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        BookmarkTopAppBar(isEditMode = isEditMode, onClickEditButton = onClickEditButton)

        if (bookmarkItems.isEmpty()) {
            BookmarkEmptyScreen()
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = listContentBottomPadding)
        ) {
            items(
                items = bookmarkItems,
                key = { item -> item.session.id }
            ) { itemState ->
                /** 편집모드 나타내는 Trailing 컨텐츠를 이곳에 구현하세요 */
                /** 편집모드 나타내는 Trailing 컨텐츠를 이곳에 구현하세요 */
                /** 편집모드 나타내는 Trailing 컨텐츠를 이곳에 구현하세요 */
                BookmarkItem(
                    leadingContent = @Composable {
//                        BookmarkTimelineItem(
//                            sequence = itemState.sequence,
//                            time = itemState.time
//                        )
                    },
                    midContent = @Composable {
                        BookmarkCard(
                            tagLabel = itemState.tagLabel,
                            types = itemState.session.types,
                            name = itemState.session.name,
                            pokemonSex = itemState.sexLabel
                        )
                    },
                    isShowTrailingContent = itemState.isEditMode,
                    trailingContent = @Composable {
                        /** 편집모드 나타내는 Trailing 컨텐츠를 이곳에 구현하세요 */
                    }
                )
            }
        }
    }
}

@Composable
private fun BookmarkEmptyScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = stringResource(id = R.string.empty_bookmark_item_description),
            style = KnightsTheme.typography.titleSmallM,
            color = DuskGray
        )
    }
}

@Composable
private fun BookmarkTopAppBar(
    isEditMode: Boolean,
    onClickEditButton: () -> Unit
) {
    val editButtonColor by animateColorAsState(
        label = "Edit Button Color Animation",
        targetValue = if (isEditMode) {
            Purple01
        } else {
            DuskGray
        }
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = stringResource(id = R.string.book_mark_top_bar_title),
            style = KnightsTheme.typography.titleSmallM,
            color = DuskGray
        )

        Text(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .clickable(onClick = onClickEditButton)
                .padding(8.dp),
            text = if (isEditMode) {
                stringResource(id = R.string.edit_button_confirm_label)
            } else {
                stringResource(id = R.string.edit_button_edit_label)
            },
            style = KnightsTheme.typography.titleSmallM,
            color = editButtonColor
        )
    }
}
