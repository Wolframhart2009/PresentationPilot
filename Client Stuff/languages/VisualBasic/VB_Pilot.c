#include <jni.h>
#include <stdio.h>
#include "VB_Pilot.h"

JNIEXPORT jint JNICALL Java_interpreter_language_VisualBasic_add
  (JNIEnv *env, jobject jobj, jint a, jint b){
	  printf("%d + %d = %d", a, b, a + b);
  }
