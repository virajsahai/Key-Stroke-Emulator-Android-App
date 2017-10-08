from bluetooth import *
from time import sleep
import SendKeys

##Synching
server_sock=BluetoothSocket(RFCOMM)
server_sock.bind(("",PORT_ANY))
server_sock.listen(1)
port=server_sock.getsockname()[1]
uuid="94f39d29-7d6d-437d-973b-fba39e49d4ee"
advertise_service(server_sock,"SampleServer",
                  service_id=uuid,
                  service_classes=[uuid,SERIAL_PORT_CLASS],
                  profiles=[SERIAL_PORT_PROFILE]
                  )
print "Waiting for Android Device."

client_sock,client_info=server_sock.accept()
print "Ready to receive"

while True:
    data=client_sock.recv(1024)
    if data=="left":
        SendKeys.SendKeys("{LEFT}")
    if data=="right":
        SendKeys.SendKeys("{RIGHT}")
    if data=="up":
        SendKeys.SendKeys("{UP}")
    if data=="down":
        SendKeys.SendKeys("{DOWN}")
    if data=="space":
        SendKeys.SendKeys("{SPACE}")
    if data=="esc":
        SendKeys.SendKeys("{ESC}")
    if data=="enter":
        SendKeys.SendKeys("{ENTER}")
        
