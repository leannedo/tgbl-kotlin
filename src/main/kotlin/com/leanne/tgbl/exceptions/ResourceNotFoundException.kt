package com.leanne.tgbl.exceptions

import java.lang.RuntimeException

class ResourceNotFoundException(name: String?, id: Long) :
    RuntimeException("$name with $id not found")
