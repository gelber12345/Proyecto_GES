package com.example.proyecto.model

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.stream.Collectors
import java.util.stream.Stream

object CommentGasolinera {
    data class Comment(
        val Autor: String,
        val Description: String,
        val fecha: LocalDateTime,
        val gasolinera: String
    )

    @RequiresApi(Build.VERSION_CODES.O)
    private val marks: MutableList<Comment> = mutableListOf(
        /*Comment(
            "Anonimo","El precio real del GLP es 12.10",
            formatToInstant("2022-06-22T11:25:44.973"),"Primax"
        ),
        Comment(
            "Anonimo","El precio real del GLP es 10.10",
            formatToInstant("2020-06-22T11:25:44.973"),"Primax"
        ),
        Comment(
            "Anonimo","El precio real del GLP es 11.10",
            formatToInstant("2021-06-22T11:25:44.973"),"Primax"
        ),*/
    )

    @RequiresApi(Build.VERSION_CODES.O)
    fun getListIterator(): MutableListIterator<Comment> {
        return marks.listIterator()
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun add(mark: Comment){
        if (!contains(mark)){
            marks.add(mark)
        }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun getList():  MutableList<Comment> {
        return marks
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun contains(mark: Comment):Boolean{
        marks.forEach(){
            if (it.fecha == mark.fecha){
                return true
            }
        }
        return false
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun getMark(nom: String): Comment {
        marks.forEach() {
            if (nom == it.gasolinera) {
                return it
            }
        }
        return marks[0];
    }
    fun addComment(valor :Comment){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            marks.add(valor)
        }
    }
    @RequiresApi(Build.VERSION_CODES.N)
    fun getComments(gasolinera: String): MutableList<Comment> {
        val list: MutableList<Comment>  = mutableListOf()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            marks.forEach(){
                if (it.gasolinera==gasolinera){
                    list.add(it)
                }
            }
        }
        list.sortByDescending { it.fecha }
        return list
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun formatToInstant(timeInString: String): LocalDateTime {

        val formatter: DateTimeFormatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.nnn")
        return LocalDateTime.parse(timeInString, formatter)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun formatToString(time: LocalDateTime): String? {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.nnn")
        return formatter.format(time)
    }
}