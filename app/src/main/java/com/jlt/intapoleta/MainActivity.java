package com.jlt.intapoleta;

import android.view.animation.Interpolator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.Spinner;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnItemSelected;

/**
 Intapoleta

 Simple tutorial to show animation interpolation in Android

 Copyright (C) 2016 Kairu Joshua Wambugu

 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program.  If not, see http://www.gnu.org/licenses/.
 */

// begin activity MainActivity
public class MainActivity extends AppCompatActivity {

    /** CONSTANTS */

    /* Strings */

    public static final String

    PACKAGE = "android.view.animation.",

    PACKAGE_V4 = "android.support.v4.view.animation.";

    /** VARIABLES */

    /* Interpolators */

    private Interpolator selectedInterpolator; // the interpolator selected by the user

    /* Primitives */

    private int selectedDuration; // the duration selected by the user

    /* Spinners */

    @Bind( R.id.am_s_interpolators )  Spinner interpolationsSpinner; // spinner to select interpolations

    @Bind( R.id.am_s_durations ) Spinner durationsSpinner; // spinner to select durations

    @Bind( R.id.am_tv ) TextView helloWorldTextView; // the hello world text view

    /** METHODS */

    /** Getters and Setters */

    /**
     * Overrides
     */

    @Override
    // begin onCreate
    protected void onCreate( Bundle savedInstanceState ) {

        // 0. super things
        // 1. use the main layout
        // 2. bind views

        // 0. super things

        super.onCreate( savedInstanceState );

        // 1. use the main layout

        setContentView( R.layout.activity_main );

        // 2. bind views

        // bind annotated fields and methods in this activity
        ButterKnife.bind( this );

    } // end onCreate

    /**
     * Other Methods
     */

    // bind a method to an OnItemSelectedListener on the view for each ID specified.
    @OnItemSelected( { R.id.am_s_durations } )
    // begin method durationSelected
    // sets the selected duration based on user choice
    void durationSelected( Spinner spinner, int position ) {

        // 0. get the selected duration as a string
        // 1a. if 100ms
        // 1a1. set the selected duration to be so
        // 1b. if 900ms
        // 1b1. set the selected duration to be so
        // 1c. if 1500ms
        // 1c1. set the selected duration to be so
        // 1d. if 3000ms
        // 1d1. set the selected duration to be so
        // 1e. if 300ms
        // 1e1. set the selected duration to be so
        // 2. do a transition using the interpolator selected

        // 0. get the selected duration as a string

        String durationString = ( String ) spinner.getAdapter().getItem( position );

        // begin switch to know which duration to use
        switch ( durationString ) {

            // 1a. if 100ms

            // 1a1. set the selected duration to be so

            case "100ms": selectedDuration = 100; break;

            // 1b. if 900ms

            // 1b1. set the selected duration to be so

            case "900ms": selectedDuration = 900; break;

            // 1c. if 1500ms

            // 1c1. set the selected duration to be so

            case "1500ms": selectedDuration = 1500; break;

            // 1d. if 3000ms

            // 1d1. set the selected duration to be so

            case "3000ms": selectedDuration = 3000; break;

            // 1e. if 300ms

            // 1e1. set the selected duration to be so

            // default is 300ms
            default: selectedDuration = 300; break;

        } // end switch to know which duration to use

        // 2. do a transition using the interpolator selected

        int item = interpolationsSpinner.getSelectedItemPosition();

        onInterpolationSelected( interpolationsSpinner, position );

    } // end method durationSelected

    // begin method findFullInterpolatorPath
    // gets the full path of the selected interpolator
    String findFullInterpolatorPath( String interpolatorName ){

        // 0. if the interpolator name starts with - (that is, it is the --------- entry in the interpolations spinner)
        // 0a. return null
        // 1. else if it is the fast out linear in or
        // the fast out slow in or
        // the linear out slow in interpolator
        // 1a. use the support package
        // 2. for all others
        // 2a. use the standard package

        // 0. if the interpolator name starts with - (that is, it is the --------- entry in the interpolations spinner)

        // 0a. return null

        if ( interpolatorName.startsWith( "-" ) == true ) { return null; }

        // 1. else if it is the fast out linear in or
        // the fast out slow in or
        // the linear out slow in interpolator

        // 1a. use the support package

        else if(
                convertToClassName( interpolatorName ).equals( "FastOutLinearInInterpolator" ) ||
                convertToClassName( interpolatorName ).equals( "FastOutSlowInInterpolator" ) ||
                convertToClassName( interpolatorName ).equals( "LinearOutSlowInInterpolator" )
                )
        { return PACKAGE_V4 + convertToClassName( interpolatorName ); }

        // 2. for all others

        // 2a. use the standard package

        else { return PACKAGE + convertToClassName( interpolatorName ); }

    } // end method findFullInterpolatorPath

    // begin method convertToClassName
    // takes a(n interpolator's) human readable name and converts it to a class name
    String convertToClassName( String humanReadableName ) {

        // 0. initialize the return string
        // 1. initialize the characters to be skipped
        // 2. go through the characters of the human readable name
        // 2a. if the current character is one to be skipped
        // 2a1. skip it
        // 2b. else the current character is not one to be skipped
        // 2b1. add the current character to the return string
        // 3. return the return string

        // 0. initialize the return string

        String returnString = "";

        // 1. initialize the characters to be skipped

        String skipCharacters = "- ";

        // 2. go through the characters of the human readable name

        // begin for through the human readable name
        for ( int i = 0; i < humanReadableName.toCharArray().length; i++ ) {

            // 2a. if the current character is one to be skipped

            // 2a1. skip it

            String currentCharacter = String.valueOf( humanReadableName.charAt( i ) );

            if ( skipCharacters.contains( currentCharacter ) == true ) { continue; }

            // 2b. else the current character is not one to be skipped

            // 2b1. add the current character to the return string

            else { returnString += currentCharacter; }

        } // end for through the human readable name

        // 3. return the return string

        return returnString;

    } // end method convertToClassName

    @OnItemSelected( { R.id.am_s_interpolators } )
    // begin method onInterpolationSelected
    // OnItemSelected for when an interpolator is chosen from the interpolations spinner
    void onInterpolationSelected( Spinner spinner, int position ) {

        // 0. get the name of the chosen interpolator
        // 1. use the display metrics of the current device
        // 2. set the hello world text view to be as high as the height of the current screen
        // 3. get the path to the selected interpolator in the packages
        // 3a. if the path is null
        // 3a1. terminate execution
        // 4. create an interpolator from the path
        // 5. animate the text view using the interpolator
        // 5a. we will use the chosen duration
        // 5b. we will wait for 500 seconds before starting animation
        // 5c. we will move the text view down through screen height
        // 5d. start animation

        // 0. get the name of the chosen interpolator

        String interpolatorName = ( String ) spinner.getAdapter().getItem( position );

        // 1. use the display metrics of the current device

        DisplayMetrics displayMetrics = new DisplayMetrics();

        // sets the passed metrics to have the current display's metrics
        getWindowManager().getDefaultDisplay().getMetrics( displayMetrics );

        // 2. set the hello world text view to be as high as the height of the current screen

        //setTranslationY - Sets the vertical location of this view relative to its top position.
        // adds the passed value to the coordinate of the top of the view
        // heightPixels - The absolute height of the display in pixels
        helloWorldTextView.setTranslationY( displayMetrics.heightPixels );

        // begin try to try animation
        try {

            // 3. get the path to the selected interpolator in the packages

            String interpolatorPath = findFullInterpolatorPath( interpolatorName );

            // 3a. if the path is null

            // 3a1. terminate execution

            if ( interpolatorPath == null ) { return; }

            // 4. create an interpolator from the path

            selectedInterpolator = ( Interpolator ) Class.forName( interpolatorPath ).newInstance();

            // 5. animate the text view using the interpolator

            helloWorldTextView
                    .animate()
                    .setInterpolator( selectedInterpolator )

            // 5a. we will use the chosen duration

                    .setDuration( selectedDuration )

            // 5b. we will wait for 500 seconds before starting animation

                    .setStartDelay( 500 )

            // 5c. we will move the text view down through screen height

                    // translationYBy - translationY be animated by the specified value
                    .translationYBy( -displayMetrics.heightPixels )

            // 5d. start animation

                    .start();

        } // end try to try animation

        // catch issues

        catch( Exception e ) { e.printStackTrace(); }

    } // end method onInterpolationSelected

    // stub method onNothingSelected
    // value - View ID(s) to which this method will be bound.
    // callback - Listener callback to which the method will be bound.
    @OnItemSelected( value = R.id.am_s_interpolators, callback = OnItemSelected.Callback.NOTHING_SELECTED )
    void onNothingSelected() {}


} // end activity MainActivity