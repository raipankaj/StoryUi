# StoryUi
Add stories to your app built with Jetpack Compose with a single composable that supports Material 3.

Stories can be beneficial for your app users who prefer to view content in slide, such feature is extensively seen in an image or video intensive apps, 
chat apps or video calling apps. Now adding such features to your app is as simple as calling a single composable function that accepts images as a url 
and load them automatically inside the story ui.

To get started with StoryUi just add the following path in settings.gradle
```gradle
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
	}
}
```
Thereafter add the following dependencies
```gradle
dependencies {
  implementation 'com.github.raipankaj:StoryUi:1.0.0'
  implementation 'androidx.compose.foundation:foundation:1.4.0-rc01'
  implementation("io.coil-kt:coil-compose:2.2.2")
}
```

Now in order to add StoryUi to any part of your app just call the "Story" Composable which accepts the following parameters
```kotlin
@Composable
fun Story(
    modifier: Modifier = Modifier,
    urlList: List<String>,
    swipeTime: Int = 5_000,
    indicator: Indicator = StoryIndicator.singleIndicator(),
    onAllStoriesShown: () -> Unit
) 
```
<li>modifier - This modifier is for the entire Story Composable</li>
<li>urlList - It requires a simple list of url as string that will get loaded using Coil</li>
<li>swipeTime - This indicates the indicator duration before swiping to the next image</li>
<li>indicator - That will help to customize the indicator for the story</li>
<li>onAllStoriesShown - Once all stories are shown, this callback will get triggered</li>

<br>
Two types of indicators are supported with this library
<li>Single type indicator on the screen</li>
<li>Multiple type indicator on the screen</li>

<br>
<h3>Single type indicator</h3>
Set the single type indicator by setting the "<b>indicator</b>" parameter to <b>StoryIndicator.singleIndicator()</b>
To customize this indicator you may pass various parameters
```kotlin
fun singleIndicator(
  indicatorColor: Color = Color.White,
  indicatorTrackColor: Color = Color.Gray,
  modifier: Modifier = Modifier.fillMaxWidth()
)
```
<li>indicatorColor - Set the color of the indicator</li>
<li>indicatorTrackColor - Set the background color for the indicator</li>
<li>modifier - Set the modifier to add background, padding, shape etc for the indicator</li>

<br>
<h3>Multiple type indicator</h3>
Set the muliple type indicator by setting the "<b>indicator</b>" parameter to <b>StoryIndicator.multiIndicator()</b>
To customize this indicator you may pass various parameters
```kotlin
fun multiIndicator(
  indicatorColor: Color = Color.White,
  indicatorTrackColor: Color = Color.Gray,
  modifier: Modifier = Modifier.fillMaxWidth(),
  indicatorPadding: PaddingValues = PaddingValues(),
  indicatorSpacing: Dp = 4.dp
)
```
<li>indicatorColor - Set the color of the indicator</li>
<li>indicatorTrackColor - Set the background color for the indicator</li>
<li>modifier - Set the modifier to add background, padding, shape etc for the indicator</li>
<li>indicatorPadding - Set the padding for the overall indicator</li>
<li>indicatorSpacing - Set the spaces between individual indicator</li>


<br><br>
Here's the demo video for multiple type indicator
<br>
[![Demo](https://github.com/raipankaj/StoryUi/blob/main/multi_indicator_stories.gif)](https://youtu.be/d0_tH6FfWuo)
