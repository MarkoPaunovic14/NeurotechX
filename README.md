# NeurotechX

The idea of the project was alerting all the people that are activly participating in the traffic of possible threats from drivers that are sleepy.

We used muse to track the state of the driver, training was done by imitating falling asleep
	when the state that is close to falling asleep happen, it will notify the driver that is falling asleep that he should have some rest, or simply leave the traffic
	Also it will notify other useres that are near him, so that they should have extra care when driving
	
	
There are lot of possible way to improve the project, but for the time that we had, we managed to train
wave filtering, and catch the signal using python, python then connects to the web server that will then change the users state sleepy to 1 which means he is about to fall asleep, that happens in the database (we were using mongoDB).
We used localNetwork to run the server and the database, so some modifications would be needed for it to run on other devices
