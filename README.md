# corner-food-market-website

Possible Issues While Running:

    Mac OS:

        - docker: Error response from daemon: Ports are not available: listen tcp 0.0.0.0:5432: bind: address already in use.

            Solution:

                1. Run "sudo lsof -i :5432", and take note of the PID.
                2. Run "sudo kill <PID>", replacing "<PID>" with the one given in step 1.
