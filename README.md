# BluetoothManager
Tool for managing Bluetooth devices connected to Raspberry PI. Allows to disconnect them, check which are currently connected.

_TODO_: 
 - Add Hazelcast for storing information about devices
 - Protect method for connecting to prevent Race Condition
 - Add endpoints for:
   - connecting to device
   - disconnecting the device
 - Add service for refreshing the statuses of available devices
 - Provide simple frontend with table containing devices and buttons for switching
 - Deploy on _raspberrypi_ using _Docker_
