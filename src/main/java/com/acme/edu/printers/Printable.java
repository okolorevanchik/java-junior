package com.acme.edu.printers;

import com.acme.edu.exceptions.SendingDataOverNetworkException;
import com.acme.edu.exceptions.WritingDataToFileException;

public interface Printable {
    void print(String message) throws SendingDataOverNetworkException, WritingDataToFileException;
}
