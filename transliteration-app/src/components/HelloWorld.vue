<template>
  <div id="app">
    <div>
      <h3>Input Text</h3>
      <textarea v-model="inputText" @input="onInputTextChange"></textarea>
    </div>
    <div>
      <h3>Output Text</h3>
      <div class="output-container">
        <textarea v-model="outputText" ref="output"></textarea>
      </div>
      <div>
        <button class="copy-button" ref="copyBtn" @click="onCopyClick">Copy to Clipboard</button>
      </div>
    </div>
    <div>
      <label for="from-alphabet">From Alphabet: </label>
      <select name="from-alphabet" id="from-alphabet" v-model="fromAlphabet" @change="onAlphabetChange">
        <option v-for="alphabet in alphabets" :value="alphabet.name" :key="alphabet.id">{{ alphabet.name }}</option>
      </select>
      <label for="to-alphabet">To Alphabet: </label>
      <select name="to-alphabet" id="to-alphabet" v-model="toAlphabet" @change="onAlphabetChange">
        <option v-for="alphabet in alphabets" :value="alphabet.name" :key="alphabet.id">{{ alphabet.name }}</option>
      </select>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import toastr from 'toastr'

export default {
  name: 'App',
  data() {
    return {
      alphabets: [],
      inputText: '',
      outputText: '',
      fromAlphabet: '',
      toAlphabet: '',
      isUpperCase: [],
    };
  },
  created() {
    axios.get('http://localhost:8081/alphabet')
      .then(response => {
        this.alphabets = response.data;
        this.fromAlphabet = this.alphabets[1].name;
        this.toAlphabet = this.alphabets[0].name;
        this.transliterateText();
      })
      .catch(error => console.error(error));
  },
  mounted() {
    toastr.options = {
      closeButton: true,
      newestOnTop: true,
      progressBar: true,
      positionClass: 'toast-top-center',
      preventDuplicates: true,
      showDuration: '300',
      hideDuration: '1000',
      timeOut: '5000',
      extendedTimeOut: '1000',
      showEasing: 'swing',
      hideEasing: 'linear',
      showMethod: 'fadeIn',
      hideMethod: 'fadeOut'
    }
    this.$toastr = toastr
  },
  methods: {
    onInputTextChange() {
      this.transliterateText();
    },
    onAlphabetChange() {
      this.transliterateText();
    },
    transliterateText() {
      const url = `http://localhost:8081/symbol/transliterate`;
      let promises = [];
      this.isUpperCase = [];
      for (let i = 0; i < this.inputText.length; i++) {
        let character = this.inputText.charAt(i);
        if (character.toUpperCase() === character) {
          this.isUpperCase[i] = true;
          let promise = axios.get(url, {
            params: {
              character: character.toLowerCase(),
              alphabet1: this.fromAlphabet,
              alphabet2: this.toAlphabet
            }
          });
          promises.push(promise);
        } else {
          this.isUpperCase[i] = false;
          let promise = axios.get(url, {
            params: {
              character: character,
              alphabet1: this.fromAlphabet,
              alphabet2: this.toAlphabet
            }
          });
          promises.push(promise);
        }
        
      }

      Promise.all(promises)
        .then(responses => {
          let outputText = '';
          for (let i = 0; i < this.isUpperCase.length; i++) {
            if (this.isUpperCase[i]) {
              outputText += responses[i].data.toUpperCase();
            } else{
              outputText += responses[i].data;
            }
          }
          this.outputText = outputText;
        })
        .catch(error => {
          console.error(error);
        });
    },

    onCopyClick() {
      const textarea = document.createElement('textarea');
      textarea.value = this.outputText;
      document.body.appendChild(textarea);
      textarea.select();
      document.execCommand('copy');
      document.body.removeChild(textarea);
      this.$toastr.success('Text copied to clipboard!')
    },
  },
};
</script>

<style>
  .toast-top-center {
    transform: translateX(45%);
    padding-bottom: 50%;
  }
</style>
