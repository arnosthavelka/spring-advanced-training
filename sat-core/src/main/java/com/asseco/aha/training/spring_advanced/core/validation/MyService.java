package com.asseco.aha.training.spring_advanced.core.validation;

public class MyService {

	// define spring validator
	// <bean id="validator"
	// class="org.springframework.validation.beanvalidation.LocalValidatorFactoryBean"
	// />

	// define and use my validator

	// @Service
	// public class ValidatorDTO {
	//
	// /**
	// * Class logger
	// */
	// private static Logger LOG = LoggerFactory.getLogger(ValidatorDTO.class);
	//
	// @Autowired
	// private Validator validator;
	//
	// public <D, T extends Default> void validateDTO(D dto, Class<T>
	// validationGroup) {
	// List<String> msgs = process(dto, validationGroup);
	// if (msgs.isEmpty()) {
	// return;
	// }
	// LOG.warn("Validace formuláře {} obsahuje chyby (počet={}).",
	// dto.getClass().getName(), msgs.size());
	// throw new ValidationException(msgs);
	// }
	//
	// private <D> List<String>
	// convertValidationMessages(Set<ConstraintViolation<D>> validationResult) {
	// List<String> msgs = new ArrayList<String>(validationResult.size());
	// for (ConstraintViolation<D> cv : validationResult) {
	// String msg = String.format("Hodnota pole '%s' není validní [%s]!",
	// cv.getPropertyPath(), cv.getMessage());
	// LOG.debug(msg);
	// msgs.add(msg);
	// }
	// return msgs;
	// }
	//
	// private <D, T extends Default> List<String> process(D dto, Class<T>
	// validationGroup) {
	// Set<ConstraintViolation<D>> validationResult = validator.validate(dto,
	// validationGroup);
	// List<String> msgs = convertValidationMessages(validationResult);
	// for (Field field : dto.getClass().getDeclaredFields()) {
	// // test non-collection fields
	// if (!Collection.class.isAssignableFrom(field.getType())) {
	// continue;
	// }
	// // test validable DTO
	// Type[] gti = ((ParameterizedType)
	// field.getGenericType()).getActualTypeArguments();
	// boolean doValidation = false;
	// for (Type type : gti) {
	// if (ValidableDTO.class.isAssignableFrom((Class<?>) type)) {
	// doValidation = true;
	// break;
	// }
	// }
	// if (!doValidation) {
	// continue;
	// }
	//
	// field.setAccessible(true);
	// try {
	// Collection<ValidableDTO> fieldCollection = (Collection<ValidableDTO>)
	// field.get(dto);
	// if (fieldCollection == null) {
	// continue;
	// }
	// for (ValidableDTO validableDTO : fieldCollection) {
	// List<String> fieldMsgs = process(validableDTO, validationGroup);
	// msgs.addAll(fieldMsgs);
	// }
	// } catch (IllegalArgumentException e) {
	// String msg = String.format("Chyba validace objektu %s: %s",
	// field.getType().getName(), e.getLocalizedMessage());
	// msgs.add(msg);
	// } catch (IllegalAccessException e) {
	// String msg = String.format("Chyba přístupu k objektu %s: %s",
	// field.getType().getName(), e.getLocalizedMessage());
	// msgs.add(msg);
	// }
	//
	// }
	// return msgs;
	// }
	// }

}
