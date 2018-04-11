/**
 * Configuration constants.
 *
 * @author Eddo W. Hintoso
 * @since 01 April, 2018
 */

import * as firebase from "firebase";


const config = {
  apiKey: "AIzaSyDCNMpx_L_NnmRV-HtMzbdwoCFCOws-SBU",
  authDomain: "csci310-proj2-team-j.firebaseapp.com",
  databaseURL: "https://csci310-proj2-team-j.firebaseio.com",
  projectId: "csci310-proj2-team-j",
  storageBucket: "csci310-proj2-team-j.appspot.com",
  messagingSenderId: "104207245787"
};
firebase.initializeApp(config);


// EXPORTS
export {
  config,
};