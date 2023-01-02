package com.umutcansahin.languageword.feature_word.presentation.words.components


import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.umutcansahin.languageword.feature_word.domain.util.OrderType
import com.umutcansahin.languageword.feature_word.domain.util.WordOrder

@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    wordOrder: WordOrder = WordOrder.Date(OrderType.Descending),
    onOrderChange: (WordOrder) -> Unit
) {
    Column(
        modifier = modifier,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = "Word",
                selected = wordOrder is WordOrder.Title,
                onSelect = { onOrderChange(WordOrder.Title(wordOrder.orderType)) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Date",
                selected = wordOrder is WordOrder.Date,
                onSelect = { onOrderChange(WordOrder.Date(wordOrder.orderType)) }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = "Ascending",
                selected = wordOrder.orderType is OrderType.Ascending,
                onSelect = {
                    onOrderChange(wordOrder.copy(OrderType.Ascending))
                }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Descending",
                selected = wordOrder.orderType is OrderType.Descending,
                onSelect = {
                    onOrderChange(wordOrder.copy(OrderType.Descending))
                }
            )
        }
    }
}