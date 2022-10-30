# NeurotechX

The idea of the project was alerting all the people that are activly participating in the traffic of possible threats from drivers that are sleepy. And that is how we would prevent certain number of casualities.

Muse connects to the computer via bluetooth, then blueMuse creates an lsl stream from a bluetooth stream, that openVibe uses to filter and clasify data, beetwen sleep and 'no-sleep'. Then the clasifier output is sent via lsl to python script. Python script than connects to the databese and if the condition is met it updates the user data, specifically its sleepy state. If the sleep state is set to true, signal is sent to the driver's phone (java android app), that will then play a 'person-like' sound, that will warn the driver about their sleepy state. Also when the driver's propery of sleepy is set to true in the database, it will then track other users of the app, who send their location to the database every minute or so, and will find the people that are near the driver to warn them about potential threat! If the person is warned about near sleepy driver, they will also get the notification, their phone will vibrate, and if the sound is on, they will hear about possible threat. Either one of them can cancel the notification after it is seen or heard.

	
There are lot of possible way to improve the project, but for the time that we had, we managed to train
wave filtering, and catch the signal using python, python then connects to the web server that will then change the users state sleepy to 1 which means he is about to fall asleep, that happens in the database (we were using mongoDB).
We used localNetwork to run the server and the database, so some modifications would be needed for it to run on other devices, server is made using node.js

Also, in the near future, this would be much cheaper to mass produce and would be capable of possibly saving thousands of lives! It could be incorporated in the car industry, and the software could be refined to the extend it would be extremely efficient, and autonomus!
