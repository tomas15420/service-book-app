package cz.uhk.fim.servicebookapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView handleNotFoundException(NotFoundException e){
        ModelAndView model = new ModelAndView("error-page");
        model.addObject("errorMessage", e.getMessage());
        model.addObject("responseCode", 404);
        return model;
    }
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ModelAndView handleBadRequestException(BadRequestException e){
        ModelAndView model = new ModelAndView("error-page");
        model.addObject("errorMessage", e.getMessage());
        model.addObject("responseCode", 400);
        return model;
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ModelAndView handleUnauthorizedException(BadRequestException e){
        ModelAndView model = new ModelAndView("error-page");
        model.addObject("errorMessage", e.getMessage());
        model.addObject("responseCode", 401);
        return model;
    }

    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ModelAndView handleForbiddenException(ForbiddenException e){
        ModelAndView model = new ModelAndView("error-page");
        model.addObject("errorMessage", e.getMessage());
        model.addObject("responseCode", 403);
        return model;
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ModelAndView handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e){
        ModelAndView model = new ModelAndView("error-page");
        model.addObject("errorMessage", "Chybný požadavek");
        model.addObject("responseCode", 400);
        return model;
    }

    @ExceptionHandler(NoResourceFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ModelAndView handleNoResourceFoundException(NoResourceFoundException e){
        ModelAndView model = new ModelAndView("error-page");
        model.addObject("errorMessage", "Stránka nenalezena");
        model.addObject("responseCode", 404);
        return model;
    }

}
