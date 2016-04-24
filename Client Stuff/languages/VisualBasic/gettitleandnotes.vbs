Dim notes, tempNoteFile, tempNoteObject
Dim activeSlide, slideTitle, slideNotes

On Error Resume Next
Set objPPT = CreateObject("PowerPoint.Application")
Set tempNoteObject = CreateObject("Scripting.FileSystemObject")
Set tempNoteFile = tempNoteObject.CreateTextFile("./languages/VisualBasic/currentnotes.txt", True)

Set activeSlide = objPPT.ActivePresentation.SlideShowWindow.View.Slide

slideTitle = activeSlide.Shapes.Title.TextFrame.TextRange.Text 

tempNoteFile.write(slideTitle)
tempNoteFile.write("\n")

slideNotes = activeSlide.NotesPage.Shapes(2).TextFrame.TextRange.Text
tempNoteFile.write(slideNotes)

tempNoteFile.close

Set tempNoteFile = tempNoteObject.GetFile("./languages/VisualBasic/CurrentNotes.txt")
tempNoteFile.attributes = tempNoteFile.attributes or 2