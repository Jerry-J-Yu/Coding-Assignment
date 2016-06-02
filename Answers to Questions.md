# Answers to the questions

#### What error conditions will you encounter? How should these be handled?

- There is no internet connection. In this case I would check for reachability on the first screen (MainActivity) and show an AlertDialog to ask the user to connect to the internet.
- Google Places API cannot find a place with a misspelled or weird name. I would make the API call give up after 10 seconds of futile searching and let the user know that no place can be found for their particular input, so the user won't stand there waiting forever for a place that doesn't exist.

#### Where could the user experience break? How will you prevent this?

If the user rotates the device immediately after selecting a place from the autocompleted list, the app will crash because the Retrofit call is dropped and googlePlaceData becomes null. To fix this, I can either:
- Check if googlePlaceData is null before invoking `placeDetailsView.showPlaceDetails(googlePlaceData)`
- Use RxJava in conjunction with Retrofit and cache an observable immediately after selecting a place from the autocompleted list, then after the user rotates the device the app can re-subscribed to the cached observable after the view is recreated, and continue to make the API call

#### What other improvements can be made?

- Instead of generating all possible POJO classes using www.jsonschema2pojo.org, I can manually write only the POJO classes that are needed.
- I need to figure out how to make the autocomplete list expand downwards rather than upwards because right now it blocks the "Clear Text" button.
- Add a credit card number to get 150,000 API requests/hr instead of the 1,000 right now. However I'm not going to do that because this is not a production app.
