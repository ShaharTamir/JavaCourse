set CLEAN_DIR_FLAGS=/s /q

del *.html
del *.js
del *.zip
del *.css
del *.class
del element-list
rmdir resources %CLEAN_DIR_FLAGS%
rmdir jquery %CLEAN_DIR_FLAGS%