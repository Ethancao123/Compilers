# 
#   Calcualtes the sum of the fibonnaci sequence of a number
#   @author Ethan Cao
#   @version 12/6/21
#
    .data   
        msg1: .asciiz "Input the value \n"
    .text 0x00400000
    .globl main
main:
    #prompts the user for input
    li $v0, 4
    la $a0, msg1
    syscall 
    li $v0, 5
    syscall
    move $a0, $v0
    #jumps to the fib subroutine
    subu $sp, $sp, 4
    sw $ra, ($sp)
    jal fib
    lw $ra, ($sp)
    addu $sp, $sp, 4
    move $a0, $v0
    #prints the result
    li $v0, 1
    syscall
end:
    li $v0, 10
    syscall
fib:
    #checks if n is less than or equal to one
    ble $a0, 1, return
    move $t0, $a0
    #adds n to the stack
    subu $sp, $sp, 4
    sw $a0, ($sp)
    #adds fib(n-1) to the stack
    sub $a0, $a0, 1
    subu $sp, $sp, 4
    sw $ra, ($sp)
    jal fib
    lw $ra, ($sp)
    addu $sp, $sp, 4
    #adds the result to the stack
    subu $sp, $sp, 4
    sw $v0, ($sp)
    #adds fib(n-2) to the stack
    lw $t0, 4($sp)
    sub $a0, $t0, 2
    subu $sp, $sp, 4
    sw $ra, ($sp)
    jal fib
    lw $ra, ($sp)
    addu $sp, $sp, 4
    #adds the result to the stack
    subu $sp, $sp, 4
    sw $v0, ($sp)
    #adds the two fibs together
    lw $t0, ($sp)
    addu $sp, $sp, 4
    lw $t1, ($sp)
    addu $sp, $sp, 4
    addu $v0, $t1, $t2
    #removes n from the stack
    addu $sp, $sp, 4
    jr $ra
return:
    move $v0, $a0
    jr $ra
    