package com.jp_funda.boxiful.views.result.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.jp_funda.boxiful.R
import com.jp_funda.boxiful.models.ResultStats
import com.jp_funda.boxiful.ui.theme.Blue500
import com.jp_funda.boxiful.ui.theme.Pink500

@Composable
fun ResultDetailSection(resultStats: ResultStats) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = Color.White,
        contentColor = Color.Black,
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Text(
                text = stringResource(id = R.string.result_detail),
                color = Color.Black,
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.ExtraBold,
            )
            // TODO Graph
            // Great count
            Text(
                text = "${stringResource(id = R.string.great)} ${resultStats.greatCount} ${
                    stringResource(id = R.string.result_times)
                }",
                color = Pink500,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.padding(start = 20.dp, top = 10.dp, bottom = 5.dp),
            )
            // Good count
            Text(
                text = "${stringResource(id = R.string.good)} ${resultStats.goodCount} ${
                    stringResource(id = R.string.result_times)
                }",
                color = Blue500,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.padding(start = 20.dp, bottom = 5.dp)
            )
            // Miss count
            Text(
                text = "${stringResource(id = R.string.miss)} ${resultStats.missCount} ${
                    stringResource(id = R.string.result_times)
                }",
                color = Color.Gray,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.padding(start = 20.dp, bottom = 5.dp)
            )
        }
    }
}