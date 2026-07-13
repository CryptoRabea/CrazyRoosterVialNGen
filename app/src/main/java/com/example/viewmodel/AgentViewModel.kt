package com.example.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.BuildConfig
import com.example.api.Content
import com.example.api.GenerateContentRequest
import com.example.api.Part
import com.example.api.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AgentViewModel : ViewModel() {

    private val _strategyText = MutableStateFlow<String?>(null)
    val strategyText: StateFlow<String?> = _strategyText

    private val _isGeneratingStrategy = MutableStateFlow(false)
    val isGeneratingStrategy: StateFlow<Boolean> = _isGeneratingStrategy

    private val _clipIdea = MutableStateFlow<String?>(null)
    val clipIdea: StateFlow<String?> = _clipIdea

    private val _isGeneratingClip = MutableStateFlow(false)
    val isGeneratingClip: StateFlow<Boolean> = _isGeneratingClip

    private val _scriptIdea = MutableStateFlow<String?>(null)
    val scriptIdea: StateFlow<String?> = _scriptIdea

    private val _isGeneratingScript = MutableStateFlow(false)
    val isGeneratingScript: StateFlow<Boolean> = _isGeneratingScript

    fun generateStrategy() {
        viewModelScope.launch {
            _isGeneratingStrategy.value = true
            try {
                val prompt = "You are an expert AI content strategist. Generate a high-retention 30-day viral short-form video strategy based on current trends. Output exactly 3 short bullet points highlighting the core strategy and themes."
                val result = callGemini(prompt)
                _strategyText.value = result
            } catch (e: Exception) {
                _strategyText.value = "Error generating strategy: ${e.message}"
            } finally {
                _isGeneratingStrategy.value = false
            }
        }
    }

    fun generateClipIdea() {
        viewModelScope.launch {
            _isGeneratingClip.value = true
            try {
                val prompt = "You are an expert AI short-form video director. Generate a highly engaging, high-retention 30-second viral clip script or concept. Keep it concise, highlighting the hook, body, and call-to-action."
                val result = callGemini(prompt)
                _clipIdea.value = result
            } catch (e: Exception) {
                _clipIdea.value = "Error generating clip: ${e.message}"
            } finally {
                _isGeneratingClip.value = false
            }
        }
    }

    fun generateScriptIdea(topic: String) {
        viewModelScope.launch {
            _isGeneratingScript.value = true
            try {
                val prompt = "You are an expert AI short-form video director. Generate a highly engaging, high-retention 30-second viral clip script for the topic: '$topic'. Keep it concise, outlining the hook, body, and call-to-action."
                val result = callGemini(prompt)
                _scriptIdea.value = result
            } catch (e: Exception) {
                _scriptIdea.value = "Error generating script: ${e.message}"
            } finally {
                _isGeneratingScript.value = false
            }
        }
    }

    private suspend fun callGemini(prompt: String): String = withContext(Dispatchers.IO) {
        val apiKey = BuildConfig.GEMINI_API_KEY
        val request = GenerateContentRequest(
            contents = listOf(Content(parts = listOf(Part(text = prompt))))
        )
        val response = RetrofitClient.service.generateContent(apiKey, request)
        response.candidates?.firstOrNull()?.content?.parts?.firstOrNull()?.text ?: "No content returned."
    }
}
