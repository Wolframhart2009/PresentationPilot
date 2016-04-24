Dim notes, tempNoteFile, tempNoteObject
Dim activeSlide, slideTitle, slideNotes
Dim strPath

On Error Resume Next

Set objPPT = CreateObject("PowerPoint.Application")
Set tempNoteObject = CreateObject("Scripting.FileSystemObject")

strPath = tempNoteObject.GetParentFolderName(WScript.ScriptFullName)
Set tempNoteFile = tempNoteObject.CreateTextFile(strPath & "\currentnotes.txt", True)

If Err.Number <> 0 Then
	'MsgBox("Permission error here!")
	Err.Clear
End if

Set activeSlide = objPPT.ActivePresentation.SlideShowWindow.View.Slide

If Err.Number <> 0 Then
	'MsgBox("No Presentation Open!")
	tempNoteFile.writeLine("")
	tempNoteFile.write("")
    tempNoteFile.close
	Err.Clear
Else
	slideTitle = activeSlide.Shapes.Title.TextFrame.TextRange.Text 

	tempNoteFile.writeLine(slideTitle)

	slideNotes = activeSlide.NotesPage.Shapes(2).TextFrame.TextRange.Text
	tempNoteFile.write(slideNotes)

	tempNoteFile.close

	Set tempNoteFile = tempNoteObject.GetFile(strPath & "\currentnotes.txt")
	tempNoteFile.attributes = tempNoteFile.attributes or 2
	temp
End If