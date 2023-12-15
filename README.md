# Fingerpicker

- My first Android App (created as part of a course of my studies together with a fellow student)

- This is a really simple FingerPicker app with which you can randomly select a person from a group of people.

- For a more refinded FingerPicker/Chooser application, see the corresponding [repository](https://github.com/UrAvgCode/Chooser) of [@UrAvgCod](https://github.com/UrAvgCode)

<br>
<img src="https://github.com/Patch4Code/AndroidFingerPickerApp/assets/116561421/e92e6c3c-6013-4a3d-9d8f-a9af7248d26f" height = 450 style="float:left; margin-right:10px; ">
<img src="https://github.com/Patch4Code/AndroidFingerPickerApp/assets/116561421/3ee15dd3-016e-4a61-917b-ca9ea66e7c2b" height = 450 style="float:left; height = 50">

#### Approach
- Main logic of the app almost entirely in the FingerPicker view class
- onTouchEvent() method to react to fingers touching the screen and store finger position and circle in HashMap

  ```
  public boolean onTouchEvent(MotionEvent event) {
    
      int pointerIndex = event.getActionIndex();
      int pointerId =v event.getPointerId(pointerIndex);

      switch (event.getActionMasked()) {
              case MotionEvent.ACTION_DOWN:
              case MotionEvent.ACTION_POINTER_DOWN:

	    //…

	    case MotionEvent.ACTION_MOVE:

	    //…

	    case MotionEvent.ACTION_UP:
	    case MotionEvent.ACTION_POINTER_UP:
	
	    //…
    ```
- onDraw() method which draws all circles stored in the HashMap
- selectWinner() method that selects the winning circle

