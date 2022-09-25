import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import log.refine.RefinedLog
import table.Header

@Composable
fun LogsView(header: Header, logs: List<RefinedLog>, detectionResultFocus: DetectionResultFocus?) {
    val listState = rememberLazyListState()

    LaunchedEffect(detectionResultFocus) {
        detectionResultFocus?.focusing?.let {
            listState.scrollToItem(it.logIndex)
        }
    }

    val divider: @Composable RowScope.() -> Unit = { ColumnDivider() }
    LazyColumn(modifier = Modifier.padding(10.dp), state = listState) {
        item { HeaderRow(header, divider) }
        item { HeaderDivider() }
        logs.forEach {
            item {
                Column {
                    LogRow(it, header, divider = divider)
                    Divider(color = Color(0xFFE5E7E9))
                }
            }
        }
    }
}