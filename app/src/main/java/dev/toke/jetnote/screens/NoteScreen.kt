package dev.toke.jetnote.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.toke.jetnote.R
import dev.toke.jetnote.components.NoteButton
import dev.toke.jetnote.components.NoteCard
import dev.toke.jetnote.components.NoteInputText
import dev.toke.jetnote.models.Note

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen( notes: List<Note>, onAddNote: (Note) -> Unit, onRemoveNote: (Note) -> Unit ) {
    var title by remember {
        mutableStateOf("")
    }
    var description by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current
//    var notes by remember {
//        mutableStateOf(mutableListOf<Note>())
//    }

    Column(modifier = Modifier.padding(6.dp)) {
        TopAppBar(title = {
                          Text(text = stringResource(id = R.string.app_name), color = Color.DarkGray)
        }, actions = {
            Icon(imageVector = Icons.Rounded.Notifications, contentDescription = "Icon", tint = Color.DarkGray)
        },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFDADFE3)))
        Column(modifier  = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally) {
            NoteInputText(text=title, label="Title", modifier = Modifier.padding(top = 9.dp, bottom = 8.dp),
                onTextChange = {value ->
                    if(value.all { char -> char.isLetter() || char.isWhitespace() }) title = value
                }, onImeAction = {})
            NoteInputText(text=description, label="Description", modifier = Modifier.padding(top = 9.dp, bottom = 8.dp), maxLine = 5,
                onTextChange = {value ->
                    if(value.all { char -> char.isLetter() || char.isWhitespace() }) description = value
                }, onImeAction = {})
            NoteButton(text = "Add", modifier = Modifier.padding(top = 9.dp, bottom = 8.dp),
                onClick = {
                    if(title.isNotEmpty() && description.isNotEmpty()) {
                        onAddNote(Note(title=title, description=description))
                        title = ""
                        description = ""
                        Toast.makeText(context, "Note added.", Toast.LENGTH_SHORT).show()

                    }
                })
            Column {
                for(note in notes) {
                    NoteCard(note, onRemoveNote)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NoteScreenPreview() {
    NoteScreen(notes = emptyList<Note>(), onAddNote = {}, onRemoveNote = {})
}