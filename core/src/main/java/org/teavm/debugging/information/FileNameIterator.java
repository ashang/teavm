/*
 *  Copyright 2014 Alexey Andreev.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.teavm.debugging.information;

public class FileNameIterator {
    private DebugInformation debugInformation;
    private int index;

    FileNameIterator(DebugInformation debugInformation) {
        this.debugInformation = debugInformation;
    }

    public boolean isEndReached() {
        return index < debugInformation.fileMapping.size();
    }

    public GeneratedLocation getLocation() {
        if (isEndReached()) {
            throw new IllegalStateException("End already reached");
        }
        return DebugInformation.key(debugInformation.fileMapping.get(index));
    }

    public int getFileNameId() {
        if (isEndReached()) {
            throw new IllegalStateException("End already reached");
        }
        return debugInformation.fileMapping.get(index).get(2);
    }

    public String getFileName() {
        int fileNameId = getFileNameId();
        return fileNameId >= 0 ? debugInformation.getFileName(fileNameId) : null;
    }

    public void next() {
        if (isEndReached()) {
            throw new IllegalStateException("End already reached");
        }
        ++index;
    }
}
