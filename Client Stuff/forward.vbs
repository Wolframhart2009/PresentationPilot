Set objPPT = CreateObject("PowerPoint.Application")

MsgBox(Application.ActivePresentation)

objPPT = Application.ActivePresentation

objPPT.SlideShowWindow.View.Next