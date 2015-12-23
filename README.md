# CA MAA - APM integration (1.0)

## Description
This integration pushes CA MAA app performance, country usage and platform crash data into APM


## Supported versions
This utility has been tested with APM 10.1, EPAgent 10.0.0.5 and CA MAA 15.4

## Supported third party versions
JDK 1.7.0_55 or higher

# License

This field pack is provided under the [Apache License, Version 2.0](https://github.com/CA-APM/MAA--APM-Integration/blob/master/LICENSE).

# Installation Instructions

1. Download and install the CA APM EPAgent. Instructions can be found at: https://communities.ca.com/docs/DOC-231150915
2. Download the maa_apm.zip from : (https://./releases/tag/maa_apm_1.0)
3. Start the EPAgent: make sure no connection errors occur
4. Save the maa_apm.zip onto the EPAgent or other available machine. Unzip into a folder and verify maa_ws.jar, apm_ws.jar and maa_apm.bat exist
5. Open the maa_apm.bat for editing
6. Modify the connection and the filter parameter

```
set EPAgentURL=<EPAgentIP>:8081
set APMdataType=IntCounter
set MAAURL="<MAAServerURL>"
set MAAtenant=<MAATenant>
set MAAuser=<user>
set MAApwd=<pwd>
set MAAperiod=<month|week|day>
set MAAstart="2015-10-01"
set MAAend="2015-11-02"
```

7. Modify the batch file by keeping or removing the data calls that are required for your particular integration.
Currently the integration supports data for application performance, geographical user sessions and crash data by platform.
Same jar files are used for each of these types. The types are distinguishable by the command line parameters like
/mdo/v1/performance/apps_summary (maa_ws.jar) or appPerformance (apm_ws.jar)

8. Run the batch file by double-clicking on it, or by typing its name in command prompt. Result:

```
================= Fetching app performance data from CA MAA ============
See apps.log for details
See geo.log for details
See crashes.log for details
See apps_alerted.log for details
================= Finished fetching data from CA MAA ===================
================= Pushing data to APM EPAgent ========================
See apps_apm.log for details
See geo_apm.log for details
See geo_apm.log for details
See apps_alerted_apm.log for details
================= Finished pushing data to APM EPAgent ==================
```

## Debugging and Troubleshooting
Any output and errors will be piped into the above log files. For more information on the operations performed, please refer to the output and
log files

## Metric description
The APMdataType variable has been tested with following values:

IntAverage (useful for response times, like "average time in seconds"; you don't calculate the average yourself;
just report all the applicable metrics (like in a loop) and the calculation will be performed automatically at the end of the interval)
IntCounter (useful for tally metrics, like "msgs in queue", and does not change until a new value is reported)

The following data types are also available for the EPAgent, but have not been tested with CA MAA data

* PerIntervalCounter (useful for rate metrics, like "miles per hour" or "errors per interval"; resets to zero at each new interval)
LongCounter  (same as above, but for very large numbers)
LongAverage (same as above, but for very large numbers
* The following data type is always used for Alerted Apps data. The variable APMdataType  -setting has no effect on it.
* StringEvent (use to report string values, like "startup command-line" or a log entry).  NOTE: StringEvents are not stored historically;
only current values are used.

## Support
This document and associated tools are made available from CA Technologies as examples and provided at no charge as a courtesy to the CA MAA Community
at large. This resource may require modification for use in your environment. However, please note that this resource is not supported by CA Technologies,
and inclusion in this site should not be construed to be an endorsement or recommendation by CA Technologies. These utilities are not covered by the
CA Technologies software license agreement and there is no explicit or implied warranty from CA Technologies. They can be used and distributed freely
amongst the CA APM Community, but not sold. As such, they are unsupported software, provided as is without warranty of any kind, express or implied,
including but not limited to warranties of merchantability and fitness for a particular purpose. CA Technologies does not warrant that this resource
will meet your requirements or that the operation of the resource will be uninterrupted or error free or that any defects will be corrected. The use
of this resource implies that you understand and agree to the terms listed herein.

Although these utilities are unsupported, please let us know if you have any problems or questions by adding a comment to the CA APM Community Site area where the resource is located, so that the Author(s) may attempt to address the issue or question.

Unless explicitly stated otherwise this field pack is only supported on the same platforms as the APM core agent. See [APM Compatibility Guide](http://www.ca.com/us/support/ca-support-online/product-content/status/compatibility-matrix/application-performance-management-compatibility-guide.aspx).


# Change log
Changes for each version of the field pack.

Version | Author | Comment
--------|--------|--------
1.0 | Janne Koponen | First version of the field pack.
