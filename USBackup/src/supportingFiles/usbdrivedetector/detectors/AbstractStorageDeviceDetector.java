/*
 * Copyright 2014 samuelcampos.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package supportingFiles.usbdrivedetector.detectors;

import java.io.File;
import java.util.List;

import supportingFiles.usbdrivedetector.USBStorageDevice;

/**
 * This class is prepared to:
 * <ul>
 * <li>Windows (XP or newer)</li>
 * <li>Mac OS X (10.7 or newer)</li>
 * </ul>
 *
 * @author samuelcampos
 */
public abstract class AbstractStorageDeviceDetector {

    private static final String OSName = System.getProperty("os.name")
            .toLowerCase();

    // private static final String OSVersion = System.getProperty("os.version");
    // private static final String OSArch = System.getProperty("os.arch");
    /**
     * {@link AbstractStorageDeviceDetector} instance. <br/>
     * This instance is created (Thread-Safe) when the JVM loads the class.
     */
    private static final AbstractStorageDeviceDetector instance;

    static {
        if (OSName.startsWith("win")) {
            instance = new WindowsStorageDeviceDetector();
        } else if (OSName.startsWith("linux")) {
            instance = new LinuxStorageDeviceDetector();
        } else if (OSName.startsWith("mac")) {
            instance = new OSXStorageDeviceDetector();
        } else {
            instance = null;
        }
    }

    public static AbstractStorageDeviceDetector getInstance() {
        if (instance == null) {
            throw new UnsupportedOperationException("Your Operative System (" + OSName + ") is not supported!");
        }

        return instance;
    }

    protected AbstractStorageDeviceDetector() {
    }

    /**
     * Returns the all storage devices currently connected to the computer.
     *
     * @return the list of the USB storage devices
     */
    public abstract List<USBStorageDevice> getStorageDevicesDevices();

    static USBStorageDevice getUSBDevice(final String rootPath) {
        return getUSBDevice(rootPath, null);
    }

    static USBStorageDevice getUSBDevice(final String rootPath, final String deviceName) {
        final File root = new File(rootPath);

        System.out.println ( "Device found: {}" + root.getPath());

        try {
            return new USBStorageDevice(root, deviceName);
        } catch (IllegalArgumentException e) {
            System.out.println ( "Could not add Device: {}" + e);
        }

        return null;
    }
}
