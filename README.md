# Fingerpicker

My first Android App (created as part of a course of my studies together with a fellow student)



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

