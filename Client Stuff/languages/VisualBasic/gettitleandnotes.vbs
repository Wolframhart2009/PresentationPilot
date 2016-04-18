Dim notes, tempNoteFile, tempNoteObject

Set objPPT = CreateObject("PowerPoint.Application")
Set tempNoteObject = CreateObject("Scripting.FileSystemObject")
Set tempNoteFile = tempNoteObject.CreateTextFile("./languages/visualbasic/currentnotes.txt", True)


tempNoteFile.close

Set tempNoteFile = tempNoteObject.GetFile("./languages/visualbasic/currentnotes.txt")
tempNoteFile.attributes = tempNoteFile.attributes or 2

MsgBox tempNoteFile.attributes