'use strict';

chrome.runtime.onInstalled.addEventListener(function() {
  chrome.storage.sync.set({color: '#3aa757'}, function() {
    console.log("The color is green.");
  });
});
