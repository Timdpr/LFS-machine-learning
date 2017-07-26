#!/usr/bin/env python2

"""Example 1: Initialize InSim and send the message 'Hello, InSim!' to the chat."""

import pyinsim
import sys

# Initialize the InSim system
insim = pyinsim.insim('127.0.0.1', 29999, Admin='')

# Send message 'Hello, InSim!' to the game
insim.sendm('--Hello, InSim!--')

try:
    import winsound
except ImportError:
    print 'Error: this example only works on Windows'
    sys.exit()

def outgauge_packet(outgauge, packet):
    # Check for shift light flag.
    if packet.ShowLights & pyinsim.DL_SHIFT:
        # Play sound.
        insim.sendm('Shift up!')
        
# Initialize OutGauge. Set timeout to 30 seconds.
outgauge = pyinsim.outgauge('127.0.0.1', 30000, outgauge_packet, 30.0)

# Start pyinsim.
pyinsim.run()
