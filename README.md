# <img src="https://cdn-icons-png.flaticon.com/512/463/463662.png" alt="drawing" width="30"/> Secure Note

This is an secure note taking demo android application

## Installation
Clone this repository and import into **Android Studio**
```bash
git clone https://github.com/alvarosct02/securenote.git
```

## How to use the app

### Note Management
1. Open the app & enter **any longer than 4 characters password**
2. Add new notes
3. Edit or Delete them

### Enabling Biometrics
1. Go to settings (top bar icon)
2. Tap on Enable biometrics
3. From now on each time you open the app a biometrics modal will pop up
   
Note: This option will only appear if you have biometrics setup on your device.

### Load Notes with a Json  file
1. Go to settings (top bar icon)
2. Tap on 'Load from Json file'
3. Select a **valid** json file (like this one: <a target="_blank" href="assets/notesSample.json" download="notesSample.json">notesSample.json</a>)

Note: The json file must follow this format:
```Json
[
	{
		"title": "Some title", 
		"messsage": "Some note" 
	}
]
```
   
Note: This option will only appear if you have biometrics setup on your device.


## Tech Stack:

- Kotlin
- MVVM
- Hilt
- Coroutines
- Flow
- ViewModel
- Room
- Navigation Component
- Databinding

## Core Features:

| Biometrics Login | List Notes | Edit Notes |
| --- | --- | --- |
| <img src="assets/IMG_3917.JPG" height="400"> | <img src="assets/IMG_3918.JPG" height="400"> | <img src="assets/IMG_3914.JPG" height="400"> |

## Other implemented features:

- Loading JSON files (<a target="_blank" href="assets/notesSample.json" download="notesSample.json">Download Json File to test</a>)
- Toogle biometrics
- Encrypted Database

| Settings Page | Import Json | Light/Night Theme |
| --- | --- | --- |
| <img src="assets/IMG_3913.JPG" height="400"> | <img src="https://github.com/alvarosct02/securenote/assets/4575788/b20bccab-82fd-49d6-822f-b02956fefa8e" height="400"> | <img src="https://github.com/alvarosct02/securenote/assets/4575788/e94817b4-e76e-4142-b716-fa33b67ff7ed" height="400"> |


## Maintainers
This project is mantained by:
* [Alvaro Santa Cruz](http://github.com/alvarosct02)

## Contributing

1. Fork it
2. Create your feature branch (git checkout -b my-new-feature)
3. Commit your changes (git commit -m 'Add some feature')
4. Push your branch (git push origin my-new-feature)
5. Create a new Pull Request
