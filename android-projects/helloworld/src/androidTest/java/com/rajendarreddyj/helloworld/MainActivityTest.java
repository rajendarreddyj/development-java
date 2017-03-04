package com.rajendarreddyj.helloworld;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

/**
 * Created by rajendarreddy on 9/15/15.
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity>{
    private MainActivity mainActivity;
    private TextView textView;
    private String resourceAsString;

    public MainActivityTest(){
        super(MainActivity.class);
    }

    protected void setUp() throws Exception {
        super.setUp();
        mainActivity=this.getActivity();
        textView = (TextView)mainActivity.findViewById(R.id.main_textview);
        resourceAsString = mainActivity.getString(R.string.hi_android);
    }

    public void testPreConditions(){
        assertNotNull(mainActivity);
    }

    public void testText(){
        assertNotNull(resourceAsString);
        assertNotNull(textView.getText());
    }
}
