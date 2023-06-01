package dev.toke.jetnote.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import dev.toke.jetnote.models.Note

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun NoteInputText(modifier: Modifier = Modifier,
                  text: String,
                  label: String,
                  maxLine: Int = 1,
                  onTextChange: (String) -> Unit,
                  onImeAction: () -> Unit) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val containerColor = Color.Transparent
    TextField(value = text, onValueChange = onTextChange,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = containerColor,
            unfocusedContainerColor = containerColor,
            disabledContainerColor = containerColor,
        ), maxLines = maxLine,
        label = { Text(text=label)},
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = {
            onImeAction()
            keyboardController?.hide()
        }),
        modifier = modifier
    )
}

@Composable
fun NoteButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    enabled: Boolean = true
) {
    Button(onClick=onClick, shape = CircleShape,
        enabled = enabled,
        modifier = modifier) {
        Text(text = text)
    }
}

@Composable
fun NoteCard(note: Note, onRemove: (Note) -> Unit) {
    Card(shape = RectangleShape, modifier = Modifier.fillMaxWidth().clip(
        RoundedCornerShape(topEnd = 33.dp, bottomStart = 33.dp)).padding(12.dp)
        .clickable { onRemove(note) }
    ) {
        Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
            Text(text = note.title, style = MaterialTheme.typography.headlineMedium, modifier = Modifier.padding(8.dp))
            Text(text = "When: ${note.date.dayOfWeek.name}", style = MaterialTheme.typography.labelSmall, modifier = Modifier.padding(top = 2.dp, bottom = 2.dp, start=8.dp, end=8.dp))
            Text(text = note.description, style = MaterialTheme.typography.bodySmall, modifier = Modifier.padding(8.dp))

        }
    }
}