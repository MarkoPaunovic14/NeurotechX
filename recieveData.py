import time
from pylsl import StreamInlet, resolve_stream
from urllib.request import urlopen
from bs4 import BeautifulSoup

def main():
    # first resolve an EEG stream on the lab network
    print("looking for an EEG stream...")
    streams = resolve_stream('type', 'EEG_1')

    # create a new inlet to read from the stream
    inlet = StreamInlet(streams[0])

    boolValue = 1

    while True:
        # get a new sample (you can also omit the timestamp part if you're not
        # interested in it)
        sample, timestamp = inlet.pull_sample()
        # print(sample[0])
        if sample[0] > 0.85 and boolValue:
            boolValue = 0
            print("sleeping")
            url = "http://192.168.0.13:3000/setSleepy/?id=3"
            html = urlopen(url).read()
            print(html)
        print(sample)
            
        if sample[0] < 0.3:
            boolValue = 1

if __name__ == '__main__':
    main()