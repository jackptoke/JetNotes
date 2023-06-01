package dev.toke.jetnote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.toke.jetnote.datas.NoteData
import dev.toke.jetnote.models.Note
import dev.toke.jetnote.screens.NoteScreen
import dev.toke.jetnote.ui.theme.JetNoteTheme

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetNoteTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val notes = remember{
                        mutableStateListOf<Note>()
                    }
                    NoteScreen(notes, onAddNote = { note ->
                        notes.add(note)
                    }
                    , onRemoveNote = {note ->
                        notes.remove(note)
                        } )
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun JetNotePreview() {
    JetNoteTheme {
        NoteScreen(NoteData().loadNotes(), {}, {})
    }
}