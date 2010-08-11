Getting started with Riena on RAP 

(experimental)

1. Install the RAP tooling into your SDK
   http://eclipse.org/rap/downloads/
   
2. Use RAP 1.3 M6 or later as your target.
   http://eclipse.org/rap/downloads/

3. Import Riena (HEAD) and additional Equinox code into your workspace using
   riena_on_rap-pserver.psf which is found in the org.eclipse.riena.releng.rap 
   project. (use riena_on_rap-ssh2.psf is you are a committer)
   
   User: anonymous
   Password: <blank> 
   
   (CVS via pserver may not work if you are behind a corporate proxy / firewall)
   
4. Ignore compiler errors. Currently only some of Riena ridget's are supported.

5. Launch 'RAP Mail Sample (RCP UI)' in 
   org.eclipse.riena.sample.app.client.rcpmail
